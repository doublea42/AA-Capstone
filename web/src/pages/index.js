import BindingClass from "../util/bindingClass";
import PawDorableClient from "../api/pawDorableClient";
import DataStore  from "../util/DataStore";
import Header from "../components/header";

class IndexPage extends BindingClass{

    constructor(){
        super();

        this.bindClassMethods(['mount', 'login', 'logout'], this)

        // this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
        // this.header = new Header(this.dataStore);

        this.client = new PawDorableClient();

    }

    mount(){
        // document.getElementById('logout').addEventListener('click', this.login);
        document.getElementById('sign-up').addEventListener('click', this.login)
    }

    async login(){
        await this.client.login();
    }

    logout(){
        this.client.logout();
    }

   

}

const main = async () => {
    const indexPage = new IndexPage();
    indexPage.mount();
};

window.addEventListener('DOMContentLoaded', main);