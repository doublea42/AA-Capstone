import BindingClass from "../util/bindingClass";
import PawDorableClient from "../api/pawDorableClient";
import DataStore  from "../util/DataStore";
import Header from "../components/header";

class CreateUpdateProfile extends BindingClass{

    constructor(){
        super();

        this.bindClassMethods(['mount', 'login', 'logout', 'redirectHomePage',
                                'getDataFromForm'], this)


        this.client = new PawDorableClient();
        this.getDataFromForm();
    }

    mount(){
        document.getElementById('home-page').addEventListener('click', this.redirectHomePage);
        document.getElementById('sign-up').addEventListener('click', this.login);
        const firstName = document.getElementById('firstName');
        const lastName = document.getElementById('lastName');
        const age = document.getElementById('age');
        document.getElementById('submit').addEventListener('click', this.getDataFromForm);
        
    }

    async getDataFromForm(){

        console.log(firstName.value);
        console.log(lastName.value);
        console.log(age.value);
        this.client.CreateProfile(firstName,lastName,age);
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

   

}

const main = async () => {
    const createUpdateProfile = new CreateUpdateProfile();
    createUpdateProfile.mount();
};

window.addEventListener('DOMContentLoaded', main);