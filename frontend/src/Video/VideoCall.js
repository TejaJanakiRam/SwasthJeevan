import React, { useEffect, useRef, useState } from "react";
import SimplePeer from "simple-peer";

const ConnectionStatus = {
  OFFERING: 0,
  RECEIVING: 1,
  CONNECTED: 2,
};

const webSocketConnection = new WebSocket("ws://localhost:4000/videochat");

export const VideoCall = () => {
  const videoSelf = useRef(null);
  const videoCaller = useRef(null);
  const [connectionStatus, setConnectionStatus] = useState(null);
  const [offerSignal, setOfferSignal] = useState();
  const [simplePeer, setSimplePeer] = useState();

  useEffect(() => {
    webSocketConnection.onmessage = (message) => {
      const payload = JSON.parse(message.data);
      if (payload?.type === "offer") {
        setOfferSignal(payload);
        setConnectionStatus(ConnectionStatus.RECEIVING);
      } else if (payload?.type === "answer") simplePeer?.signal(payload);
    };
  }, [simplePeer]);

  const sendOrAcceptInvitation = (isInitiator, offer) => {
    navigator.mediaDevices.getUserMedia({ video: true, audio: true }).then((mediaStream) => {
      const video = videoSelf.current;
      video.srcObject = mediaStream;
      video.play();

      const sp = new SimplePeer({
        trickle: false,
        initiator: isInitiator,
        stream: mediaStream,
      });

      if (isInitiator) setConnectionStatus(ConnectionStatus.OFFERING);
      else offer && sp.signal(offer);

      sp.on("signal", (data) => webSocketConnection.send(JSON.stringify(data)));
      sp.on("connect", () => setConnectionStatus(ConnectionStatus.CONNECTED));
      sp.on("stream", (stream) => {
        const video = videoCaller.current;
        video.srcObject = stream;
        video.play();
      });
      setSimplePeer(sp);
    });
  };

  return (
    <div className="py-24 px-2 h-screen w-screen">
      {connectionStatus === null && <button onClick={() => sendOrAcceptInvitation(true)}>CALL</button>}
      {connectionStatus === ConnectionStatus.OFFERING && <div className="loader"></div>}
      {connectionStatus === ConnectionStatus.RECEIVING && (
        <button onClick={() => sendOrAcceptInvitation(false, offerSignal)}>ANSWER CALL</button>
      )}
      <div className="video-container h-auto flex items-center w-auto">
        <div class=" rounded overflow-hidden shadow-lg h-auto">
          <div class="px-6 py-4 h-auto">
            <div class="font-bold text-xl mb-2">The Coldest Sunset</div>
            <div>
              <video ref={videoCaller} className="h-max w-auto p-2" />
              
            </div>
            
          </div>
          <div class="px-6 pt-4 pb-2 h-auto flex justify-center items-center">
            <button class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2" onclick="muteAudio()">
                Mute Audio
            </button>
            <button class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2" onclick="muteVideo()">
                Mute Video
            </button>
            <button class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2" onclick="endCall()">
                End Call
            </button>
        </div>

        </div>
        
        <div class="max-w-sm rounded overflow-hidden shadow-lg">
          <div class="px-6 py-1">
            <div class="font-bold text-l mb-2">The Coldest Sunset</div>
            <div>
              <video ref={videoSelf} className="w-52 h-52" />
            </div>
            
          </div>
        </div>

      </div>
    </div>
  );
};
export default VideoCall;