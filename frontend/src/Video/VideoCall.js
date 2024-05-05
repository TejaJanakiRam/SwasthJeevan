// import React, { useEffect, useRef, useState } from "react";
// import SimplePeer from "simple-peer";

// const ConnectionStatus = {
//   OFFERING: 0,
//   RECEIVING: 1,
//   CONNECTED: 2,
// };

// const webSocketConnection = new WebSocket("ws://localhost:4000/videochat");

// export const VideoCall = () => {
//   const videoSelf = useRef(null);
//   const videoCaller = useRef(null);
//   const [connectionStatus, setConnectionStatus] = useState(null);
//   const [offerSignal, setOfferSignal] = useState();
//   const [simplePeer, setSimplePeer] = useState();

//   useEffect(() => {
//     webSocketConnection.onmessage = (message) => {
//       const payload = JSON.parse(message.data);
//       if (payload?.type === "offer") {
//         setOfferSignal(payload);
//         setConnectionStatus(ConnectionStatus.RECEIVING);
//       } else if (payload?.type === "answer") simplePeer?.signal(payload);
//     };
//   }, [simplePeer]);

//   const sendOrAcceptInvitation = (isInitiator, offer) => {
//     navigator.mediaDevices.getUserMedia({ video: true, audio: true }).then((mediaStream) => {
//       const video = videoSelf.current;
//       video.srcObject = mediaStream;
//       video.play();

//       const sp = new SimplePeer({
//         trickle: false,
//         initiator: isInitiator,
//         stream: mediaStream,
//       });

//       if (isInitiator) setConnectionStatus(ConnectionStatus.OFFERING);
//       else offer && sp.signal(offer);

//       sp.on("signal", (data) => webSocketConnection.send(JSON.stringify(data)));
//       sp.on("connect", () => setConnectionStatus(ConnectionStatus.CONNECTED));
//       sp.on("stream", (stream) => {
//         const video = videoCaller.current;
//         video.srcObject = stream;
//         video.play();
//       });
//       setSimplePeer(sp);
//     });
//   };

//   return (
//     <div className=" h-screen w-screen">
//       {connectionStatus === null && <button onClick={() => sendOrAcceptInvitation(true)}>CALL</button>}
//       {connectionStatus === ConnectionStatus.OFFERING && <div className="loader"></div>}
//       {connectionStatus === ConnectionStatus.RECEIVING && (
//         <button onClick={() => sendOrAcceptInvitation(false, offerSignal)}>ANSWER CALL</button>
//       )}
//       <div className="video-container h-3/4 flex items-center w-screen justify-around">
//         <div className=" rounded overflow-hidden shadow-lg w-1/2">
//           <div class="px-6 py-4 h-full">
//             <div class="font-bold text-xl mb-2">The Coldest Sunset</div>
//             <div class>
//               <video ref={videoCaller} className="h-max w-full p-2 " />
              
//             </div>
            
//           </div>


//         </div>
        
//         <div class="rounded overflow-hidden shadow-lg w-1/4">
//           <div class="px-6 py-1">
//             <div class="font-bold text-l mb-2">My video</div>
//             <div>
//               <video ref={videoSelf} className="w-max h-max" />
//             </div>
            
//           </div>
//         </div>

//       </div>
//       <div class="p-4 py-20 flex justify-center items-center w-full">
//             <button class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2" onclick="toggleAudio()">
//                 Toggle Audio
//             </button>
//             <button class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2" onclick="toggleVideo()">
//                 Toggle Video
//             </button>
//             <button class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2" onclick="endCall()">
//                 End Call
//             </button>
//         </div>
//     </div>
//   );
// };
// export default VideoCall;
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
    <div className=" h-screen w-screen text-blue-500">
      <h2 className='p-4 text-center text-4xl mb-4 font-semibold text-blue-500'>Video Call</h2>
    
      {connectionStatus === null && <button className="text-center text-4xl mb-4 font-semibold" onClick={() => sendOrAcceptInvitation(true)}>CALL</button>}
      {connectionStatus === ConnectionStatus.OFFERING && <div className="loader"></div>}
      {connectionStatus === ConnectionStatus.RECEIVING && (
        <button className="text-center text-4xl mb-4 font-semibold" onClick={() => sendOrAcceptInvitation(false, offerSignal)} >ANSWER CALL</button>
      )}
      
      <div className="video-container h-3/4 flex items-center w-screen justify-around">
        <div className=" rounded overflow-hidden shadow-lg w-1/2">
          <div className="px-6 py-4 h-full">
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
      <div className="h-1 flex md:justify-center xl:justify-end justify-end items-center w-full">
        <button className="m-4 text-xl sm:text-2xl  px-4 py-3 sm:px-8 sm:py-6 rounded-2xl border-2 border-white text-blue-500 bg-white hover:text-blue-600 hover:bg-gray-100 hover:border-gray-50 transition-all duration-300 font-semibold tracking-wide sm:tracking-widest shadow shadow-slate-800" onClick={toggleAudio}>
          {audioEnabled ? "Mute Audio" : "Unmute Audio"}
        </button>
        <button className="m-4 text-xl sm:text-2xl  px-4 py-3 sm:px-8 sm:py-6 rounded-2xl border-2 border-white text-blue-500 bg-white hover:text-blue-600 hover:bg-gray-100 hover:border-gray-50 transition-all duration-300 font-semibold tracking-wide sm:tracking-widest shadow shadow-slate-800" onClick={toggleVideo}>
          {videoEnabled ? "Stop Video" : "Start Video"}
        </button>
        <button className="m-4 text-xl sm:text-2xl  px-4 py-3 sm:px-8 sm:py-6 rounded-2xl border-2 border-white text-blue-500 bg-white hover:text-blue-600 hover:bg-gray-100 hover:border-gray-50 transition-all duration-300 font-semibold tracking-wide sm:tracking-widest shadow shadow-slate-800">
          {"End Call"}
        </button>
      </div>
    </div>
  );
};

export default VideoCall;
