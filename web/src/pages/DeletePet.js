import BindingClass from "../util/bindingClass";
import PawDorableClient from "../api/pawDorableClient";

class DeletePet extends BindingClass{

    constructor(){
        super();

        this.bindClassMethods(['mount', 'logout','getDataFromForm', 'pageRemovePet',
                ,'redirectProfilePage', 'redirectHomePage', 'redirectMyPets',], this)
        this.client = new PawDorableClient();
    }

    mount(){

        document.getElementById('home-page').addEventListener('click', this.redirectHomePage);
        document.getElementById('log-out').addEventListener('click', this.logout);
        document.getElementById('profile').addEventListener('click', this.redirectProfilePage);
        

        document.getElementById('submit_delete').addEventListener('click',this.getDataFromForm);


    }

    async getDataFromForm(){
        const inputdataBox = document.getElementById('pet_id');
        const inputdata = inputdataBox.value

        console.log(inputdata);

        this.pageRemovePet(inputdata);

    }

    async pageRemovePet(pet){
        const a = pet;
        
        const newDiv = document.createElement("div");
        const object = await this.client.RemovePet(a);
        
        let string = null;

        if(object == null){
            string = "pet was not remove or it didnt exist"
        }
        else{
            string = "is pet remove? " + JSON.stringify(object)
            console.log(string);

        }


        newDiv.append(string);
        const currentDiv = document.getElementById("result-body");
        document.body.insertBefore(newDiv, currentDiv);


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
    const page = new DeletePet();
    page.mount();
};

window.addEventListener('DOMContentLoaded', main);