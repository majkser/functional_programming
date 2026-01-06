FROM node:20-alpine

WORKDIR /app

RUN npm install express

COPY main.js .

EXPOSE 3000

CMD ["node", "main.js"]