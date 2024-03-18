export default function SpecialityCard({item}) {
    return (<div className='card text-xl min-w-[150px] h-[120px] lg:min-w-[200px]   m-2 lg:m-4 bg-white border-2 border-blue-500 relative flex flex-col justify-center items-center rounded-xl hover:scale-110 transition-all duration-300 hover:cursor-default hover:shadow-sm hover:shadow-slate-800'>
        <img src={item.imageUrl} className='rounded-full w-16'></img>
        <h2 className='text-sm py-2 font-semibold'>{item.name}</h2>
    </div>)
}