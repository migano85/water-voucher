# in development phase: the foundation will be a node.js image that will be used to handle reactJS requests
#---------DEV START-------------------------------------
FROM node:alpine
WORKDIR /front-end
COPY package.json .
RUN npm install
COPY . .
CMD ["npm","start"]
#---------DEV ENDS--------------------------------------