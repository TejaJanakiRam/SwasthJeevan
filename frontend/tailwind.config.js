/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",
  ],
  theme: {
    extend: {
      fontFamily: {
        blackOps: ["Black Ops One"],
        roboto: ["Roboto"]
      },
      width: {
        "112": "28rem"
      },
      minHeight: {
        "128": "32rem"
      }
    },
  },
  plugins: [
    require('tailwind-scrollbar-hide')
  ],
}

