import axios from "axios";

/*
  Axios, which is a popular library is mainly used to send asynchronous 
  HTTP requests(GET,POST,PUT,DELETE) to REST endpoints. 
This library is very useful to perform CRUD operations.
This popular library is used to communicate with the backend. 
Axios supports the Promise API, native to JS ES6.
Using Axios we make API requests in our application. 
Once the request is made we get the data in Return, and then we use this data in our React APPL. 

> npm install axios

*/
// Service class interacts with REST API

class AuthenticationService {

    static async login(dealer) {
        try {
            const response = await axios.post('http://localhost:8085/ims/api/login', dealer);
            console.log("Rest API Resonse: ", response.data);
            if (response.data == true) {
                return true;
            }
            else return false;
        }
        catch (error) {
            console.error('Login error: ', error);
        }
    }

    static async register(dealer) {
        try {
            const response = await axios.post('http://localhost:8085/ims/api/register', dealer)
            return response.data;
            // console.log("Rest API Resonse: ", response.data);
            // if (response.data == 'Reg succ') {
            //     return true;
            // }
            // else return false;
        }
        catch (error) {
            console.error('Registration error: ', error);
        }
    }

    static async getDealerInfo() {
        // return axios.get('https://localhost:8085/ims/api/dealers')
        //     .then((response) => response.data)
        //     .catch((error) => {
        //         console.error("Error fetching dealer info", error);
        //     });
        try{
            return axios.get('http://localhost:8085/ims/api/dealers');
            }
            catch(error){
                console.error("Error getting dealers")
            }
    }
}

export default AuthenticationService;
