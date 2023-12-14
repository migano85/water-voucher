# in development phase: the foundation will be a node.js image that will be used to handle reactJS requests
#---------DEV START-------------------------------------
# FROM node:alpine
# WORKDIR /front-end
# COPY package.json .
# RUN npm install
# COPY . .
# EXPOSE 3000
# CMD ["npm","start"]
#---------DEV ENDS--------------------------------------
#---------PROD START------------------------------------
FROM node:19.5.0-alpine as build 
ARG ROOT_URL_ARG
WORKDIR /app 
COPY ./package.json . 
COPY ./package-lock.json . 
RUN npm install 
COPY ./public ./public/ 
COPY ./src ./src/
RUN echo "the value of ARG is this one " ${ROOT_URL_ARG}
RUN echo "the value of ARG is $ROOT_URL_ARG"
# Setting react application environment variable, not via .env but via the next line
# .env values cannot be accessed like development phase when using and npm build in docker to move to production
# therefore we use docker compose to pass .env variables or else REACT_APP_ROOT_URL will be empty in customerSearch.js page
# in other word; customerSearch.js will access the environment variable to run, not the one in .env
# the variable inside .env is used in docker compose to pass data to ARG variable defined in this file

RUN REACT_APP_ROOT_URL=${ROOT_URL_ARG} npm run build
FROM nginx:1.17-alpine 
COPY --from=build /app/build /usr/share/nginx/html 
EXPOSE 80 
CMD ["nginx", "-g", "daemon off;"]
#---------PROD ENDS---------------------------------------