import BindingClass from "../util/bindingClass";
import PawDorableClient from "../api/pawDorableClient";
import DataStore  from "../util/DataStore";
import Header from "../components/header";

class AddPet extends BindingClass{

    constructor(){
        super();

        this.bindClassMethods(['mount', 'login', 'logout', 'redirectHomePage'], this)
        this.client = new PawDorableClient();
    }

    mount(){
        document.getElementById('home-page').addEventListener('click', this.redirectHomePage);
        document.getElementById('profile').addEventListener('click', this.redirectProfilePage);
        document.getElementById('log-out').addEventListener('click', this.logout);
        
    }

    async login(){
        await this.client.login();
    }

    logout(){
        this.client.logout();
        window.location.href = '/index.html';
    }

    redirectHomePage(){
        window.location.href = '/HomePage.html';
    }
    redirectProfilePage(){
        window.location.href = '/Profile.html';
    }
    redirectMyPetsPage(){
        window.location.href = '/MyPets.html';
    }
    redirectRentPage(){
        window.location.href = '/ComingHome.html';
    }
   

}

const main = async () => {
    const addPetPage = new AddPet();
    addPetPage.mount();
};

window.addEventListener('DOMContentLoaded', main);