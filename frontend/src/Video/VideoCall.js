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
  const [mediaStream, setMediaStream] = useState(null);
  const [audioEnabled, setAudioEnabled] = useState(true);
  const [videoEnabled, setVideoEnabled] = useState(true);

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
    navigator.mediaDevices.getUserMedia({ video: true, audio: true }).then((stream) => {
      setMediaStream(stream);
      const video = videoSelf.current;
      video.srcObject = stream;
      video.play();

      const sp = new SimplePeer({
        trickle: false,
        initiator: isInitiator,
        stream: stream,
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

  const toggleAudio = () => {
    const audioTracks = mediaStream.getAudioTracks();
    audioTracks.forEach((track) => {
      track.enabled = !audioEnabled;
    });
    setAudioEnabled(!audioEnabled);
  };

  const toggleVideo = () => {
    const videoTracks = mediaStream.getVideoTracks();
    videoTracks.forEach((track) => {
      track.enabled = !videoEnabled;
    });
    setVideoEnabled(!videoEnabled);
  };
  return (
    <div className=" h-screen w-screen">
      {connectionStatus === null && <button onClick={() => sendOrAcceptInvitation(true)}>CALL</button>}
      {connectionStatus === ConnectionStatus.OFFERING && <div className="loader"></div>}
      {connectionStatus === ConnectionStatus.RECEIVING && (
        <button onClick={() => sendOrAcceptInvitation(false, offerSignal)}>ANSWER CALL</button>
      )}
      <div className="video-container h-3/4 flex items-center w-screen justify-around">
        <div className=" rounded overflow-hidden shadow-lg w-1/2">
          <div className="px-6 py-4 h-full">
            <div className="font-bold text-xl mb-2">The Coldest Sunset</div>
            <div>
              <video ref={videoCaller} className="h-max w-full p-2 " />
            </div>
          </div>
        </div>
        <div className="rounded overflow-hidden shadow-lg w-1/4">
          <div className="px-6 py-1">
            <div className="font-bold text-l mb-2">My video</div>
            <div>
              <video ref={videoSelf} className="w-max h-max" />
            </div>
          </div>
        </div>
      </div>
      <div className="p-4 py-20 flex justify-center items-center w-full">
        <button className="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2" onClick={toggleAudio}>
          {audioEnabled ? "Mute Audio" : "Unmute Audio"}
        </button>
        <button className="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2" onClick={toggleVideo}>
          {videoEnabled ? "Stop Video" : "Start Video"}
        </button>

      </div>
    </div>
  );
};

export default VideoCall;
