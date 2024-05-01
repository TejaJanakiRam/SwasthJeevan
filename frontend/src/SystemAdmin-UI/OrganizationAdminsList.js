import { useState } from "react";
import { FaSearch } from "react-icons/fa";
import DropdownSelect from "./DropdownSelect";
export default function OrganizationAdminsList(props) {
    const [organization, setOrganization] = useState('');
    return (<div className=" h-4/6">
        <div className="w-full flex justify-between">
            {props.organizationsList && <DropdownSelect data={props.organizationsList} displayFieldName={"Organization"} selectedItem={organization} setSelectedItem={setOrganization} />}
        </div>
        <ul className={`bg-white w-full mt-0 h-full rounded-xl overflow-y-scroll scroll-smooth shadow-inner shadow-slate-800 z-10`} >
            {props.data.map((item) => (
                <li className={`p-4 mx-8 block text-xl bg-blue-400 text-white hover:cursor-pointer hover:bg-blue-500 my-4 rounded-xl ${organization === '' ? "block" : (organization === item.organization.name ? "block" : "hidden")}`}>{item.name}</li>
            ))}
        </ul>
    </div>)
}