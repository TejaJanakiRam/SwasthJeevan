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

export default function RevokeConsent(props) {
    const navigate = useNavigate();
    const [ConsentsList, setConsentsList] = useState([]);
    const getRequestedConsentsList = async () => {
        try {
            const getDetailsResp = await axios.get('http://localhost:4000/api/patient/profile', {
                headers: {
                    Authorization: `Bearer ${props.token}` // Assuming props.token contains the JWT token
                }
            });
            let getConsentsURL = 'http://localhost:4000/api/consent/' + getDetailsResp.data.id;
            const response = await axios.get(getConsentsURL, {
                headers: {
                    Authorization: `Bearer ${props.token}` // Assuming props.token contains the JWT token
                },
                params: {
                    statusStr: 'PROVIDED'
                }
            });
            setConsentsList(response.data.consents)
        } catch (error) {
            console.error("Failed to fetch consents list:", error);
        }
    };

    useEffect(() => {
        getRequestedConsentsList()
    }, []);

    const [selectedRow, setSelectedRow] = useState(0);
    const handleSelectedRow = (rowIndex) => {
        setSelectedRow(rowIndex);
    }

    const [selectedRecord, setSelectedRecord] = useState(0);
    const handleSelectedRecord = (recId) => {
        setSelectedRecord(recId);
    }
    
    let idOfSelectedConsent;
    let inEndDateStr;

    const handleRevokeConsent = async()=>{
        idOfSelectedConsent = selectedRecord;
        try {
            const getDetailsResp = await axios.get('http://localhost:4000/api/patient/profile', {
                headers: {
                    Authorization: `Bearer ${props.token}` // Assuming props.token contains the JWT token
                }
            });
            let putRevokedConsentUrl = 'http://localhost:4000/api/consent/' + getDetailsResp.data.id + "/" + idOfSelectedConsent;
            const response = await axios.put(putRevokedConsentUrl, null, {
                headers: {
                    Authorization: `Bearer ${props.token}` // Assuming props.token contains the JWT token
                },
                params: {
                    statusStr: 'REVOKED',
                    endDateStr: inEndDateStr
                }
            });
            if (response.status === 200)
                console.log("Revoked Consent sucessfully");
            else
                console.log("Revoke Consent failed");
            getRequestedConsentsList()
        } catch (error) {
            console.error("Failed to revoke Consent:", error);
        }
    }

    return (
        <div>
        <Navbar role={"patient"} onLogout={props.onLogout} />
        {ConsentsList && (
            <div className="min-h-screen flex justify-center items-center bg-gradient-to-r from-blue-100 to-blue-500">
                <div className="bg-white p-10 rounded-lg shadow-md max-w">
                    <div className="font-bold text-7xl mb-10 text-center">Provided Consents List</div>
                    <TableContainer component={Paper}>
                        <Table sx={{ minWidth: 650 }} aria-label="Provided Consents List">
                            <TableHead>
                                <TableRow>
                                    <TableCell>
                                        CONSENT ID
                                    </TableCell>
                                    <TableCell align="right">
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
                                    Provide                   STATUS
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
                                {ConsentsList.map((row, index) => (
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
                    <div className="mt-10 flex justify-center">
                            <button
                                className="bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600 mr-4"
                                onClick={handleRevokeConsent}
                            >
                                Revoke Consent
                            </button>
                    </div>
                </div>
            </div>
        )}
        </div>
    );
}
