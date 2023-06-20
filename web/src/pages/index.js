import BindingClass from "../util/bindingClass";
import PawDorableClient from "../api/pawDorableClient";
import DataStore  from "../util/DataStore";
import Header from "../components/header";

class IndexPage extends BindingClass{

    constructor(){
        super();

        this.bindClassMethods(['mount', 'login', 'logout'], this)


        this.client = new PawDorableClient();
        this.header = new Header(this.DataStore);

    }

    mount(){
        document.getElementById('home-page').addEventListener('click', this.redirectHomePage);
        document.getElementById('sign-up').addEventListener('click', this.login);
        
    }

    async login(){
        await this.client.login();
        // window.location.href = '/HomePage.html';
    }

    logout(){
        this.client.logout();
    }

    redirectHomePage(){
        window.location.href = '/HomePage.html';
    }

   

}

const main = async () => {
    const indexPage = new IndexPage();
    indexPage.mount();
};

window.addEventListener('DOMContentLoaded', main);