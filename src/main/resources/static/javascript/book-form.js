
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
}


document.addEventListener("DOMContentLoaded", () => {

    const stars = document.querySelectorAll(".bi-star-fill");
    const ratingValueInput = document.getElementById("rating-value");


    stars.forEach(star => {
        star.addEventListener("click", () => {
            const ratingValue = star.getAttribute("data-value");
            ratingValueInput.value = ratingValue; 
            updateStarRating(ratingValue); 
        });
    });

    // to add selected class to stars based on the ratings
    function updateStarRating(selectedRating) {
        stars.forEach(star => {
            if (parseInt(star.getAttribute("data-value")) <= selectedRating) {
                star.classList.add("selected");
            } else {
                star.classList.remove("selected");
            }
        });
    }

    const initialRating = ratingValueInput.value;
    if (initialRating) {
        updateStarRating(initialRating);
    }
});


const maxDateFinished = document.getElementById("dateFinished").max;
const maxDateStarted = document.getElementById("dateStarted").max;

const date = new Date();

let day = date.getDate();
let month = date.getMonth() + 1;
let year = date.getFullYear();

// This arrangement can be altered based on how we want the date's format to appear.
let currentDate = `${year}-${month}-${day}`;
console.log(currentDate); // "17-6-2022"

maxDateFinished.max = new Date().toLocaleDateString('fr-ca')
maxDateStarted.max = new Date().toLocaleDateString('fr-ca')
