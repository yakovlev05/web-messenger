FROM node:21-alpine AS build
WORKDIR /app
COPY package.json /app/package.json
RUN npm install
COPY . /app
RUN npm run build

FROM node:21-alpine AS dev
WORKDIR /app
COPY package.json /app/package.json
RUN npm install
COPY . /app
EXPOSE 5173
CMD ["npm", "run", "dev",  "--", "--host"]

FROM nginx:stable AS prod
COPY --from=build /app/dist /usr/share/nginx/html
RUN rm /etc/nginx/conf.d/default.conf
COPY nginx.conf /etc/nginx/conf.d
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]