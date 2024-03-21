export default function SpecialityCard({ item }) {
    return (<div className='card text-xl min-w-[150px] h-[100px] sm:h-[140px] lg:min-w-[200px]   m-2 lg:m-4 bg-white  relative flex flex-col justify-center items-center rounded-xl hover:scale-110 transition-all duration-300 hover:cursor-default hover:shadow hover:shadow-slate-800'>
        <img src={item.imageUrl} className='rounded-full w-16'></img>
        <h2 className='text-sm py-2 font-semibold text-blue-500'>{item.name}</h2>
    </div>)
}