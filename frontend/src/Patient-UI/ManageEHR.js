import Navbar from "../Common-UI/Navbar/Navbar.js"
import axios from "axios";
import { useEffect, useState } from "react";
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { useNavigate } from "react-router-dom";

export default function ManageEHR(props) {
    const navigate = useNavigate();
    const [EHRList, setEHRList] = useState([]);
    const getEHRList = async () => {
        try {
            const getDetailsResp = await axios.get('http://localhost:4000/api/patient/profile', {
                headers: {
                    Authorization: `Bearer ${props.token}` // Assuming props.token contains the JWT token
                }
            });
            let listEHRUrl = 'http://localhost:4000/api/ehr/' + getDetailsResp.data.id;
            const response = await axios.get(listEHRUrl, {
                headers: {
                    Authorization: `Bearer ${props.token}` // Assuming props.token contains the JWT token
                }
            });
            setEHRList(response.data.ehrsMetadata)
    
        } catch (error) {
            console.error("Failed to fetch ehr list:", error);
        }
    };

    useEffect(() => {
        getEHRList();
    }, []);

    const [selectedRow, setSelectedRow] = useState(0);
    const handleSelectedRow = (rowIndex) => {
        setSelectedRow(rowIndex);
    }

    const [selectedRecord, setSelectedRecord] = useState(0);
    const handleSelectedRecord = (recId) => {
        setSelectedRecord(recId);
    }
    
    let idOfSelectedEHR;

    const handleViewEHR = async()=>{
        idOfSelectedEHR = selectedRecord;
        try {
            const getDetailsResp = await axios.get('http://localhost:4000/api/patient/profile', {
                headers: {
                    Authorization: `Bearer ${props.token}` // Assuming props.token contains the JWT token
                }
            });
            let getEHRUrl = "http://localhost:4000/api/ehr/" + getDetailsResp.data.id + "/" + idOfSelectedEHR;
            const response = await axios.get(getEHRUrl, {
                headers: {
                    Authorization: `Bearer ${props.token}`, // Assuming props.token contains the JWT token
                    responseType: 'blob'
                }
            });
            const bytes = atob(response.data.ehr.document);
            let length = bytes.length;
            let out = new Uint8Array(length);
        
            while (length--) {
                out[length] = bytes.charCodeAt(length);
            }
            const file = new Blob([out], { type: 'application/pdf' });
            const fileURL = URL.createObjectURL(file) + '#toolbar=0&navpanes=0&scrollbar=0';
            window.open(fileURL);
        } catch (error) {
            console.error("Failed to view ehr:", error);
        }
    }

    const handleDeleteEHR = async()=>{
        idOfSelectedEHR = selectedRecord;
        try {
            const getDetailsResp = await axios.get('http://localhost:4000/api/patient/profile', {
                headers: {
                    Authorization: `Bearer ${props.token}` // Assuming props.token contains the JWT token
                }
            });
            let deleteEHRUrl = "http://localhost:4000/api/ehr/" + getDetailsResp.data.id + "/" + idOfSelectedEHR;
            const response = await axios.delete(deleteEHRUrl, {
                headers: {
                    Authorization: `Bearer ${props.token}` // Assuming props.token contains the JWT token
                }
            });
            getEHRList()
        } catch (error) {
            console.error("Failed to delete ehr:", error);
        }
    }

    return (
        <div>
        <Navbar role={"patient"} onLogout={props.onLogout} />
        {EHRList && (
            <div className="min-h-screen flex justify-center items-center bg-gradient-to-r from-blue-100 to-blue-500">
                <div className="bg-white p-10 rounded-lg shadow-md max-w">
                    <div className="font-bold text-7xl mb-10 text-center">EHR Listing</div>
                    <TableContainer component={Paper}>
                        <Table sx={{ minWidth: 650 }} aria-label="EHR List">
                            <TableHead>
                                <TableRow>
                                    <TableCell>
                                        EHR ID
                                    </TableCell>
                                    <TableCell align="right">
                                        USER ID
                                    </TableCell>
                                    <TableCell align="right">
                                        TYPE
                                    </TableCell>
                                    <TableCell align="right">
                                        DIAGNOSIS
                                    </TableCell>
                                    <TableCell align="right">
                                        ISSUE DATE
                                    </TableCell>
                                    <TableCell align="right">
                                        END DATE
                                    </TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {EHRList.map((row, index) => (
                                    <TableRow
                                        key={row.id}
                                        sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                                        selected={selectedRecord === row.id}
                                        onMouseOver={() => handleSelectedRow(row.id)}
                                        onClick={() => handleSelectedRecord(row.id)}
                                    >
                                        <TableCell component="th" scope="row">
                                            {row.id}
                                        </TableCell>
                                        <TableCell align="right">
                                            {row.userId}
                                        </TableCell>
                                        <TableCell align="right">
                                            {row.type}
                                        </TableCell>
                                        <TableCell align="right">
                                            {row.diagnosisType}
                                        </TableCell>
                                        <TableCell align="right">
                                            {row.issueDate}
                                        </TableCell>
                                        <TableCell align="right">
                                            {row.endDate}
                                        </TableCell>
                                    </TableRow>
                                ))}
                            </TableBody>
                        </Table>
                    </TableContainer>
                    <div className="mt-10 flex justify-center">
                            <button
                                className="bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600 mr-4"
                                onClick={handleViewEHR}
                            >
                                View EHR
                            </button>
                            <button
                                className="bg-red-500 text-white px-4 py-2 rounded-lg hover:bg-red-600"
                                onClick={handleDeleteEHR}
                            >
                                Delete EHR
                            </button>
                    </div>
                </div>
            </div>
        )}
        </div>
    );
}
