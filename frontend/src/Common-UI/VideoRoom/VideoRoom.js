import { useParams } from "react-router-dom"
import {ZegoUIKitPrebuilt} from '@zegocloud/zego-uikit-prebuilt'

export default function VideoRoom(){
    const {roomId}  = useParams();
    
    const MyMeeting = async (element) => {
        const appID = 276886615;
        const serverSecret = "47efde376a6ec11933091e7a08391884";
        const kitToken = ZegoUIKitPrebuilt.generateKitTokenForTest(appID,serverSecret,roomId,Date.now().toString(),"Teja");
        const zc = ZegoUIKitPrebuilt.create(kitToken);
        zc.joinRoom({
            container: element,
            scenario:{
                mode: ZegoUIKitPrebuilt.OneONoneCall,
            }
        })
    }
    return(
            <div ref={MyMeeting}/>
    )
}