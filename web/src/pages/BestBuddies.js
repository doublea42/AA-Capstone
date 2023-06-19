import BindingClass from "../util/bindingClass";
import PawDorableClient from "../api/pawDorableClient";
import DataStore  from "../util/DataStore";
import Header from "../components/header";

class BestBuddies extends BindingClass{

    constructor(){
        super();

        this.bindClassMethods(['mount', 'login', 'logout',
         'redirectHomePage', 'redirectProfilePage', 'redirectMyPetsPage', 'redirectRentPage'], this)

        this.client = new PawDorableClient();

    }

    mount(){
        document.getElementById('home-page').addEventListener('click', this.redirectHomePage);
        document.getElementById('log-out').addEventListener('click', this.logout);
        document.getElementById('profile').addEventListener('click', this.redirectProfilePage);
        document.getElementById('my-pets').addEventListener('click', this.redirectMyPetsPage);
        document.getElementById('rent').addEventListener('click', this.redirectRentPage);
        
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
    redirectMyPetsPage(){
        window.location.href = '/MyPets.html';
    }
    redirectRentPage(){
        window.location.href = '/ComingHome.html';
    }

   

}

const main = async () => {
    const Page = new BestBuddies();
    Page.mount();
};

window.addEventListener('DOMContentLoaded', main);