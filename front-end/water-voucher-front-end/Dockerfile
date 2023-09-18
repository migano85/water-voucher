FROM node:alpine
WORKDIR /water-voucherfront-end-app
COPY package.json ./
RUN npm install
COPY . .
CMD ["npm","start"]