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
    return (<form className='flex flex-col items-center w-full min-h-3/4 justify-center'>
        <div className="w-full flex px-16 py-8 flex-col justify-around">
            <div className="p-3">
                <label htmlFor="date" className="w-full block text-lg font-semibold text-blue-500">Date</label>
                <input type="date" id="date" name="date" className="w-full text-blue-500 border-2 border-blue-500 rounded-xl py-2 px-3 focus:outline-none  focus:border-blue-800 focus:text-blue-800 sm:text-sm lg:text-lg" autoComplete="off" />
            </div>
            <div className="p-3">
                <label htmlFor="time" className="w-full block text-lg font-semibold text-blue-500">Time</label>
                <input type="time" id="time" name="time" className="w-full text-blue-500 border-2 border-blue-500 rounded-xl py-2 px-3 focus:outline-none  focus:border-blue-800 focus:text-blue-800 sm:text-sm lg:text-lg" autoComplete="off" />
            </div>
            <div className="w-full flex justify-between">
                <DropdownSelect data={hospitalData} displayFieldName={"Hospital"} />
                <DropdownSelect data={specialtyData} displayFieldName={"Speciality"} />
            </div>


        </div>
    </form>)
}