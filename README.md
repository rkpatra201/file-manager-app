# file-manager-app
google drive file api

* follow here to create drive api client_secret.json. without client_secret.json file the application won't start.
https://o7planning.org/en/11917/create-credentials-for-google-drive-api

* I have placed my client_secret.json file at below location.
![image](https://user-images.githubusercontent.com/17001948/46134788-5b0d1380-c261-11e8-8055-38675d145d5a.png)

* start the application: mvn spring-boot:run
![image](https://user-images.githubusercontent.com/17001948/46134486-9eb34d80-c260-11e8-8e95-dbf7314f9724.png)

* stop the cmd. go to the above url in your browser.
![image](https://user-images.githubusercontent.com/17001948/46134647-0bc6e300-c261-11e8-98b6-38dc665e75f8.png)

* allow access for the app.
![image](https://user-images.githubusercontent.com/17001948/46134703-2ef19280-c261-11e8-8eb0-b78585afe70c.png)

* now restart the app with : mvn spring-boot:run
![image](https://user-images.githubusercontent.com/17001948/46135203-4bda9580-c262-11e8-984d-ee19e3c90f7d.png)

* access the root folder which is created on startup
![image](https://user-images.githubusercontent.com/17001948/46135310-82181500-c262-11e8-8e75-83b46a485595.png)

* GET root folder content
![image](https://user-images.githubusercontent.com/17001948/46136807-091abc80-c266-11e8-9432-2a76fd2a964e.png)

* Upload a file under root folder
![image](https://user-images.githubusercontent.com/17001948/46137028-7e868d00-c266-11e8-8f4d-84cc894e8dc5.png)

* Create a folder called images under root folder.
![image](https://user-images.githubusercontent.com/17001948/46137303-f359c700-c266-11e8-9fc3-8f260a404921.png)

* View root folder content
http://localhost:8080/sub/1ijyEPSrXXQP4aCi8VDNN24AB-k9lv9L1
```json
  {
"createdTime": {
"value": 1538040994582,
"dateOnly": false,
"timeZoneShift": 0
},
"id": "1Cj1N_wRkJUFlbDArtVlQBaNJXm6coSo6",
"name": "images",
"parents": [
  "1ijyEPSrXXQP4aCi8VDNN24AB-k9lv9L1"
],
},
  {
"createdTime": {
"value": 1538040770665,
"dateOnly": false,
"timeZoneShift": 0
},
"id": "1liALnsldw2alJlJ4_UWP06hD70cgmCD0",
"name": "dd-amount.pdf",
"parents": [
  "1ijyEPSrXXQP4aCi8VDNN24AB-k9lv9L1"
],
}
```
* Upload an image file to root/images
![image](https://user-images.githubusercontent.com/17001948/46137618-a3c7cb00-c267-11e8-8b0b-b2bdef2c2ec4.png)

* Get content of root/images
![image](https://user-images.githubusercontent.com/17001948/46137674-c2c65d00-c267-11e8-81a8-37995afbe287.png)

* Get the image file details
![image](https://user-images.githubusercontent.com/17001948/46137751-e8ebfd00-c267-11e8-95d7-cec15049172b.png)

* Get the image in html page.
![image](https://user-images.githubusercontent.com/17001948/46137855-19339b80-c268-11e8-9f9b-8bf63d157634.png)

![image](https://user-images.githubusercontent.com/17001948/46137876-2781b780-c268-11e8-80ff-0ea30a48b7eb.png)

* View the image in google drive.
![image](https://user-images.githubusercontent.com/17001948/46137950-4e3fee00-c268-11e8-82da-de86e1f9dafd.png)

* Root folder content in google drive
![image](https://user-images.githubusercontent.com/17001948/46137994-6283eb00-c268-11e8-8050-7ad382a6c0e3.png)

* Delete the image: DELETE http://localhost:8080/1XZpyRNdgLwiT5tRy-Md42ybVIGvBRP4-

* View the content of root/image folder.
![image](https://user-images.githubusercontent.com/17001948/46138164-d0c8ad80-c268-11e8-8d54-1fd1929eeace.png)

![image](https://user-images.githubusercontent.com/17001948/46138242-ffdf1f00-c268-11e8-8b01-f3493e2422bc.png)

![image](https://user-images.githubusercontent.com/17001948/46138214-ef2ea900-c268-11e8-9b83-45bbb792c4b3.png)






