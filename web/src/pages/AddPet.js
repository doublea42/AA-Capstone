import BindingClass from "../util/bindingClass";
import PawDorableClient from "../api/pawDorableClient";
import DataStore  from "../util/DataStore";
import Header from "../components/header";

class AddPet extends BindingClass{

    constructor(){
        super();

        this.bindClassMethods(['mount', 'login', 'logout', 'redirectHomePage', 'pageCreatePet',
         'getDataFromForm', 'getHTMLForSearchResults'], this)
        this.client = new PawDorableClient();
    }

    mount(){
        document.getElementById('home-page').addEventListener('click', this.redirectHomePage);
        document.getElementById('profile').addEventListener('click', this.redirectProfilePage);
        document.getElementById('log-out').addEventListener('click', this.logout);
        document.getElementById('submitPet').addEventListener('click', this.getDataFromForm);

        
    }

    async getDataFromForm(){
        const petName = document.getElementById('petName').value;
        const kind = document.getElementById('pet-kind').value;
        const petsAge = document.getElementById('pet-age').value;
        const petsSex = document.getElementById('pets-gender').value;
        const rentable = document.getElementById('rent').value;

        

        console.log(petName);
        console.log(kind);
        console.log(petsAge);
        console.log(petsSex);
        console.log(rentable);

        const Cpet = this.pageCreatePet(petName,kind,petsAge,petsSex,rentable);
    }

    

    
    async pageCreatePet(name, kind, age, sex, available){
        const a = name;
        const b = kind;
        const c = age;
        const d = sex;
        const e = available;
        const newDiv = document.createElement("div");
        const object = await this.client.CreatePet(a,b,c,d,e);

        newDiv.innerHTML = this.getHTMLForSearchResults(object);
        // const string = JSON.stringify(object)
        // console.log(string);
        // newDiv.append(string);
        const currentDiv = document.getElementById("result-body");
        document.body.insertBefore(newDiv, currentDiv);


    }


    getHTMLForSearchResults(searchResults) {
        console.log(searchResults , "from getHTMLForSearchResults");
            //    if (!searchResults || !searchResults.allEventList || searchResults.allEventList.length === 0) {
            //        return '<h4>No results found</h4>';
            //    }
               let html = "";
                    console.log(searchResults);
                   html += `
                   <tr>
                   Your Pet Info:
                   <br><br>
                   <td>
                            ${searchResults.id}
                    </td>
                    <td>
                            ${searchResults.name}
                     </td>
                     <td>
                            ${searchResults.kindOfPet}
                      </td>
                       <td>
                            ${searchResults.age}
                     </td>
                     <td>
                             ${searchResults.gender}
                    </td>
                       
                   </tr><br>`;
               return html;
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