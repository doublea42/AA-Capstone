import BindingClass from "../util/bindingClass";

class IndexPage extends BindingClass{

    constructor(){
        super();

        this.bindClassMethods(['mount', 'login', 'logout'], this)




    }

    mount(){
        document.getElementById('logout').addEventListener('click', this.login);
        document.getElementById('sign-up').addEventListener('click', this.login)
    }

    async login(){
        await this.client.login();
    }

    logout(){
        this.client.logout();
    }

    main = async () => {
        const IndexPage = new IndexPage();
        IndexPage.mount();
    };

}

window.addEventListener('DOMContentLoaded', main);