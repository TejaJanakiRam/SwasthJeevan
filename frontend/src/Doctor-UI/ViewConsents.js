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

export default function ViewConsents(props) {
    const navigate = useNavigate();
    const [ConsentList, setConsentList] = useState([]);
    const getConsentsList = async () => {
        try {
            const getDetailsResp = await axios.get('http://localhost:4000/api/doctor/profile', {
                headers: {
                    Authorization: `Bearer ${props.token}` // Assuming props.token contains the JWT token
                }
            });
            const curConsultation = await axios.get('http://localhost:4000/api/consultation_request/', {
                headers: {
                    Authorization: `Bearer ${props.token}` // Assuming props.token contains the JWT token
                },
                params: {
                    doctorIdStr: getDetailsResp.data.id.toString(),
                    statusStr: 'BOOKED'
                }
            });
            let listConsetsURL = 'http://localhost:4000/api/consent/' + curConsultation.data.consultationRequests[0].patientId;
            const response = await axios.get(listConsetsURL, {
                headers: {
                    Authorization: `Bearer ${props.token}` // Assuming props.token contains the JWT token
                },
                params: {
                    doctorIDStr: getDetailsResp.data.id.toString()
                }
            });
            setConsentList(response.data.consents)

        } catch (error) {
            console.error("Failed to fetch ehr list:", error);
        }
    };

    useEffect(() => {
        getConsentsList();
    }, []);

    const [selectedRow, setSelectedRow] = useState(0);
    const handleSelectedRow = (rowIndex) => {
        setSelectedRow(rowIndex);
    }

    const [selectedRecord, setSelectedRecord] = useState(0);
    const handleSelectedRecord = (recId) => {
        setSelectedRecord(recId);
    }

    return (
        <div>
        <Navbar role={"doctor"} onLogout={props.onLogout} />
        {ConsentList && (
            <div className="min-h-screen flex justify-center items-center bg-gradient-to-r from-blue-100 to-blue-500">
                <div className="bg-white p-10 rounded-lg shadow-md max-w">
                    <div className="font-bold text-7xl mb-10 text-center">Consent Listing</div>
                    <TableContainer component={Paper}>
                        <Table sx={{ minWidth: 650 }} aria-label="Consent List">
                            <TableHead>
                                <TableRow>
                                    <TableCell>
                                        TYPE
                                    </TableCell>
                                    <TableCell align="right">
                                        PATIENT ID
                                    </TableCell>
                                    <TableCell align="right">
                                        DOCTOR ID
                                    </TableCell>
                                    <TableCell align="right">
                                        EHR ID
                                    </TableCell>
                                    <TableCell align="right">
                                        STATUS
                                    </TableCell>
                                    <TableCell align="right">
                                        START DATE
                                    </TableCell>
                                    <TableCell align="right">
                                        END DATE
                                    </TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {ConsentList.map((row, index) => (
                                    <TableRow
                                        key={row.id}
                                        sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                                        selected={selectedRecord === row.id}
                                        onMouseOver={() => handleSelectedRow(row.id)}
                                        onClick={() => handleSelectedRecord(row.id)}
                                    >
                                        <TableCell component="th" scope="row">
                                            {row.type}
                                        </TableCell>
                                        <TableCell align="right">
                                            {row.patientId}
                                        </TableCell>
                                        <TableCell align="right">
                                            {row.doctorId}
                                        </TableCell>
                                        <TableCell align="right">
                                            {row.ehrId}
                                        </TableCell>
                                        <TableCell align="right">
                                            {row.status}
                                        </TableCell>
                                        <TableCell align="right">
                                            {row.startDate}
                                        </TableCell>
                                        <TableCell align="right">
                                            {row.endDate}
                                        </TableCell>
                                    </TableRow>
                                ))}
                            </TableBody>
                        </Table>
                    </TableContainer>
                </div>
            </div>
        )}
        </div>
    );
}
