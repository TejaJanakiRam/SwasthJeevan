# ---------- Build Stage ----------
FROM node:24.6.0-alpine3.22 AS build
WORKDIR /app

# Install dependencies
COPY package*.json ./
RUN npm ci

COPY . .
RUN npm run build    

# ---------- Production Stage ----------
FROM nginx:stable-alpine3.21-perl AS production
COPY --from=build /app/build /usr/share/nginx/html
EXPOSE 3000

CMD ["nginx", "-g", "daemon off;"]
