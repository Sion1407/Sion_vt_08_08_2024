const http = require('http');
const fs = require('fs');
const path = require('path');

const filePath = path.join(__dirname, 'index.html');

const serveFile = (port) => {
  const server = http.createServer((req, res) => {
    fs.readFile(filePath, (err, data) => {
      if (err) {
        res.writeHead(404);
        res.end("File not found");
      } else {
        res.writeHead(200, { 'Content-Type': 'text/html' });
        res.end(data);
      }
    });
  });

  server.listen(port, () => {
    console.log(`Server running at http://localhost:${port}/`);
  });
};

serveFile(8001);
serveFile(8002);
