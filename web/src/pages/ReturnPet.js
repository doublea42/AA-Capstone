import BindingClass from "../util/bindingClass";
import PawDorableClient from "../api/pawDorableClient";
import DataStore  from "../util/DataStore";
import Header from "../components/header";

class ReturnPet extends BindingClass{

    constructor(){
        super();

        this.bindClassMethods(['mount', 'login', 'logout', 'redirectHomePage',
        'redirectMyPets', 'redirectComingHome'], this)
        this.client = new PawDorableClient();
    }

    mount(){
        document.getElementById('home-page').addEventListener('click', this.redirectHomePage);
        document.getElementById('log-out').addEventListener('click', this.logout);
        document.getElementById('coming-home').addEventListener('click', this.redirectComingHome);
        document.getElementById('my-pets').addEventListener('click', this.redirectMyPets);
        document.getElementById('profile').addEventListener('click', this.redirectProfilePage);

        
    }

    async login(){
        await this.client.login();
    }

    logout(){
        this.client.logout();
        window.location.href = '/index.html';
    }

    redirectProfilePage(){
        window.location.href = '/Profile.html';
    }

    redirectHomePage(){
        window.location.href = '/HomePage.html';
    }

    redirectMyPets(){
        window.location.href = '/MyPets.html';
    }

    redirectComingHome(){
        window.location.href = '/ComingHome.html';
    }

}

const main = async () => {
    const Page = new ReturnPet();
    Page.mount();
};

window.addEventListener('DOMContentLoaded', main);