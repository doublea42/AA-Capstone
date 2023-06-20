import BindingClass from "../util/bindingClass";
import PawDorableClient from "../api/pawDorableClient";
import DataStore  from "../util/DataStore";
import Header from "../components/header";

class HomePage extends BindingClass{

    constructor(){
        super();

        this.bindClassMethods(['mount', 'login', 'logout', 'redirectHomePage', 'redirectProfilePage',
                                'redirectMyPetsPage', 'redirectRentPage', 'clientLoaded'], this)


        this.client = new PawDorableClient();

    }

    mount(){
        document.getElementById('home-page').addEventListener('click', this.redirectHomePage);
        document.getElementById('profile').addEventListener('click', this.redirectProfilePage);
        // document.getElementById('sign-up').addEventListener('click', this.login);
        document.getElementById('log-out').addEventListener('click', this.logout);
        document.getElementById('rent').addEventListener('click', this.redirectRentPage);
        document.getElementById('my-pets').addEventListener('click', this.redirectMyPetsPage);
        
        this.clientLoaded();
        
    }

    async clientLoaded(){
        const identity = await this.client.getIdentity();
        const profileEmail = identity.email;

        const allPets = await this.client.getAllPets(profileEmail);
        
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
    const homePage = new HomePage();
    homePage.mount();
};

window.addEventListener('DOMContentLoaded', main);