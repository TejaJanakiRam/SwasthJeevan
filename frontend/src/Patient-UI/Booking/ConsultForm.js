import DropdownSelect from "./DropdownSelect.js";

export default function ConsultForm() {
    const hospitalData = [
        {
            id: 1,
            name: "Apollo Delhi"
        },
        {
            id: 2,
            name: "Sarvodaya-8"
        },
        {
            id: 3,
            name: "Sarvodaya-19"
        },
        {
            id: 4,
            name: "Apollo Faridabad"
        },
        {
            id: 5,
            name: "Medanta"
        },
        {
            id: 6,
            name: "QRG"
        },
        {
            id: 7,
            name: "Fortis"
        },
        {
            id: 8,
            name: "AIIMS"
        }
    ];
    const specialtyData = [
        {
            id: 1,
            name: "Cardiology"
        },
        {
            id: 2,
            name: "Orthopedics"
        },
        {
            id: 3,
            name: "Neurology"
        },
        {
            id: 4,
            name: "Oncology"
        },

    ]
    return (<form className='flex flex-col items-center w-full min-h-3/4 justify-center'>
        <div className="w-full flex px-16 py-8 flex-col justify-around">

            <div className="w-full flex justify-between">
                <DropdownSelect data={hospitalData} displayFieldName={"Hospital"} />
                <DropdownSelect data={specialtyData} displayFieldName={"Speciality"} />
            </div>

        </div>
        <button type="submit" className=" font-semibold border-2 border-blue-400 bg-blue-400 text-white rounded-xl px-10 py-2 my-8  hover:bg-blue-500 hover:border-blue-500 transition-all duration-300 shadow shadow-slate-900">Find Doctors</button>
    </form>)
}