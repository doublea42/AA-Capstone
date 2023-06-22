import BindingClass from "../util/bindingClass";
import PawDorableClient from "../api/pawDorableClient";
import DataStore  from "../util/DataStore";
import Header from "../components/header";

class CreateUpdateProfile extends BindingClass{

    constructor(){
        super();

        this.bindClassMethods(['mount', 'login', 'logout', 'redirectHomePage',
                                'getDataFromForm', 'getDataFromForm','redirectProfilePage'], this)


        this.client = new PawDorableClient();
    }

    mount(){
        
        document.getElementById('home-page').addEventListener('click', this.redirectHomePage);
        document.getElementById('sign-up').addEventListener('click', this.login);
        const firstName = document.getElementById('firstName');
        const lastName = document.getElementById('lastName');
        const age = document.getElementById('age');
        document.getElementById('submit').addEventListener('click', this.getDataFromForm);
        this.loadProfile();
        
    }

    async getDataFromForm(){

        const identity = await this.client.getIdentity();
        const profileEmail = identity.email;
        const fname = firstName.value;
        const lname = lastName.value;
        const newAge = age.value;

        console.log(fname);
        console.log(lname);
        console.log(newAge);

        let update = null;

        update = this.client.UpdateProfile(profileEmail, fname,lname,newAge);


        this.redirectProfilePage();
        
        

    }

    async loadProfile(){


        const identity = await this.client.getIdentity();
        const profileEmail = identity.email;
        console.log(profileEmail);
        const newDiv = document.createElement("div");
        const allPets = await this.client.getProfile(profileEmail);
        const string = JSON.stringify(allPets)
        console.log(string);
        newDiv.append(string);
        const currentDiv = document.getElementById("result-body");
        document.body.insertBefore(newDiv, currentDiv);
        
        
    }


    async login(){
        await this.client.login();
    }

    logout(){
        this.client.logout();
    }

    redirectHomePage(){
        window.location.href = '/HomePage.html';
    }

    redirectProfilePage(){
        window.location.href = '/Profile.html';
    }

    

   

}

const main = async () => {
    const createUpdateProfile = new CreateUpdateProfile();
    createUpdateProfile.mount();
};

window.addEventListener('DOMContentLoaded', main);