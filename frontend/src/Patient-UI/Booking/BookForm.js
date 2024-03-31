import DropdownSelect from "./DropdownSelect.js";

export default function BookForm() {
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
    return (<form className='flex flex-col items-center w-full min-h-3/4 justify-center p-16'>
        <div className="w-full flex justify-between">
            <div className="p-3 w-full">
                <label htmlFor="date" className="w-full block text-lg font-semibold text-blue-500">Date</label>
                <input type="date" id="date" name="date" className="w-full text-blue-500 border-2 border-blue-500 rounded-xl py-2 px-3 focus:outline-none  focus:border-blue-800 focus:text-blue-800 sm:text-sm lg:text-lg" autoComplete="off" />
            </div>
            <div className="p-3 w-full">
                <label htmlFor="time" className="w-full block text-lg font-semibold text-blue-500">Time</label>
                <input type="time" id="time" name="time" className="w-full text-blue-500 border-2 border-blue-500 rounded-xl py-2 px-3 focus:outline-none  focus:border-blue-800 focus:text-blue-800 sm:text-sm lg:text-lg" autoComplete="off" />
            </div>
        </div>
        <div className="w-full flex justify-between">
            <DropdownSelect data={hospitalData} displayFieldName={"Hospital"} />
            <DropdownSelect data={specialtyData} displayFieldName={"Speciality"} />
        </div>
        <button type="submit" className=" font-semibold border-2 border-blue-400 bg-blue-400 text-white rounded-xl px-10 py-2 my-8  hover:bg-blue-500 hover:border-blue-500 transition-all duration-300 shadow shadow-slate-900">Find Doctors</button>
    </form>)
}