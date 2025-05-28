const mainForm = document.getElementById('main-form');

window.addEventListener('scroll', () => {
    if (window.scrollY > 0) {
        mainForm.classList.add('pt-90');
    } else {
        mainForm.classList.remove('pt-90');
    }
});
