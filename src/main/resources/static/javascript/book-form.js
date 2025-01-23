function cancelForm(){
    window.location = "[[@{/books}]]";
}

function toggleFields() {
    const status = document.querySelector('input[name="status"]:checked').value;
    const dateStarted = document.getElementById("dateStarted");
    const dateFinished = document.getElementById("dateFinished");
    const rating = document.getElementById("rating");
    const review = document.getElementById("review");

if (status === "WANT_TO_READ") {
    // Disable all fields when the status is "Want to Read"
    dateStarted.disabled = true;
    dateFinished.disabled = true;
    rating.disabled = true;
    review.disabled = true;
} else {
    dateStarted.disabled = false;
    dateFinished.disabled = false;              
    rating.disabled = false;
    review.disabled = false;
 
}

document.addEventListener("DOMContentLoaded", () => {
const today = new Date().toISOString().split("T")[0];
const dateStarted = document.getElementById("dateStarted");
const dateFinished = document.getElementById("dateFinished");
})


}

function sortAscending(){

}

function sortDescending(){

}
