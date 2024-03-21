import { useState, useEffect } from 'react'
import FAQCard from './FAQCard'
export default function FAQ({ data }) {
    return (
        <div className='py-12 border-b-2 bg-blue-400'>
            <h2 className='text-center text-4xl mb-4 font-semibold text-white'>FAQs</h2>
            <div className='m-8 flex flex-col items-center justify-center'>
                {data.map((item) => <FAQCard key={item.id} item={item} />)}
            </div>
        </div>)
}