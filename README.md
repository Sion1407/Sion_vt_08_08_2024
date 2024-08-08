BASIC INFO ABOUT THE FOLDER STRUCTURE:

 - app consists of all the backend api's which is built on spring boot
 - for Front end it consists of a html page and a server.js to instantiate multiple instances of html page (but still it requires config, in future i will config it in nginx)

HOW TO RUN:
 - Install postgreSQL and import the table with data given in urls.csv file
 - Start the backend server and also not that create one more instantiate another instance of spring boot at -Dserver.port=8080 (add it in VM option in Edit configs)
 - Start the server.js by running cmd node server.js
 - Then run the nginx.exe inside nginx folder


Then you are good to go
Navigate to http://localhost:8081, there you go enter any url it will give you shorten version of it

Below image shows with the help of nginx I have achieved loadbalancing which take care of availablilty of the servers, as my backend servers are running on 8080 and 9090 ports it is distributing the network traffic on these 2 instances
![image](https://github.com/user-attachments/assets/50433406-27a7-4a9b-9f71-54f4556d44f5)

