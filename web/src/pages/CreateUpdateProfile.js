import BindingClass from "../util/bindingClass";
import PawDorableClient from "../api/pawDorableClient";
import DataStore  from "../util/DataStore";
import Header from "../components/header";

class CreateUpdateProfile extends BindingClass{

    constructor(){
        super();

        this.bindClassMethods(['mount', 'login', 'logout', 'redirectHomePage'], this)

        // this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
        // this.header = new Header(this.dataStore);

        this.client = new PawDorableClient();

    }

    mount(){
        document.getElementById('home-page').addEventListener('click', this.redirectHomePage);
        document.getElementById('sign-up').addEventListener('click', this.login);
        
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