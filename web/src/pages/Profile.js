import BindingClass from "../util/bindingClass";
import PawDorableClient from "../api/pawDorableClient";
import DataStore  from "../util/DataStore";
import Header from "../components/header";
import Authenticator from "../api/authenticator";

class Profile extends BindingClass{

    constructor(){
        super();

        this.bindClassMethods(['mount', 'login', 'logout', 'redirectHomePage','redirectMyPets',
         'redirectUpdateProfile', 'redirectComingHome', 'redirectBuddies', 'redirectBestBuddies', 'redirectProfilePage'], this)
        this.client = new PawDorableClient();
    }

    mount(){
        document.getElementById('home-page').addEventListener('click', this.redirectHomePage);
        document.getElementById('profile').addEventListener('click', this.redirectProfilePage);
        document.getElementById('log-out').addEventListener('click', this.logout);
        document.getElementById('coming-home').addEventListener('click', this.redirectComingHome);
        document.getElementById('my-pets').addEventListener('click', this.redirectMyPets);
        document.getElementById('update-profile').addEventListener('click', this.redirectUpdateProfile);
        document.getElementById('buddies').addEventListener('click', this.redirectBuddies);
        document.getElementById('best-buddies').addEventListener('click', this.redirectBestBuddies);
        document.getElementById('coming-home-1').addEventListener('click', this.redirectComingHome);
        document.getElementById('my-pets-1').addEventListener('click', this.redirectMyPets);


        
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

    redirectMyPets(){
        window.location.href = '/MyPets.html';
    }

    redirectUpdateProfile(){
        window.location.href = '/Create_Update_Profile.html';
    }

    redirectComingHome(){
        window.location.href = '/ComingHome.html';
    }

    redirectBuddies(){
        window.location.href = '/Buddies.html';
    }

    redirectBestBuddies(){
        window.location.href = '/BestBuddies.html';
    }


   

}

const main = async () => {
    const Page = new Profile();
    Page.mount();
};

window.addEventListener('DOMContentLoaded', main);