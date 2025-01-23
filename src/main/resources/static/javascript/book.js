// Function to hide the alert after 3-5 seconds
setTimeout(() => {
    const alertMessage = document.getElementById("alert-message");
    if (alertMessage) {
        alertMessage.classList.add("fade-out"); // Add fade-out class for smooth transition
        setTimeout(() => {
        alertMessage.remove(); // Remove element completely after fade-out
        }, 1000); // Wait for transition to complete (1 second)
    }
}, 1000); // Initial delay of 3 seconds