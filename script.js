const carousel = document.querySelector('.carousel');
const arrowBtn = document.querySelectorAll('.wrapper > button');
let cards = [...carousel.querySelectorAll('.card')];
let cardOffset = cards.map(card => card.offsetLeft);
window.addEventListener('resize', () => {
    cards = [...carousel.querySelectorAll('.card')];
    cardOffset = cards.map(card => card.offsetLeft);
});

arrowBtn.forEach(btn => {
    btn.addEventListener('click', () => {
        const scrollTo = (n) => {
            return carousel.querySelector(`.card:nth-child(${n})`).offsetLeft;
        }
        let nthCard = cardOffset.findIndex(offset => offset > carousel.scrollLeft);
        if (cardOffset[nthCard - 1] === carousel.scrollLeft) {
            carousel.scrollLeft = btn.id === "left" ? scrollTo(nthCard - 1) : scrollTo(nthCard + 1);
        }
        else {
            carousel.scrollLeft = btn.id === "left" ? scrollTo(nthCard) : scrollTo(nthCard + 1);
        }
        console.log(cardOffset);
        console.log(carousel.scrollLeft);
    });
});

let isDragging = false, startX, startScrollLeft;

function startDrag(e) {
    isDragging = true;
    carousel.classList.add('dragging');
    startX = e.pageX;
    startScrollLeft = carousel.scrollLeft;
}

function dragging(e) {
    if (!isDragging) return; // if not dragging, do nothing
    carousel.scrollLeft = startScrollLeft - (e.pageX - startX) * 1.5;
}

function stopDrag() {
    isDragging = false;
    carousel.classList.remove('dragging');

}

carousel.addEventListener('mousedown', startDrag);
carousel.addEventListener('mousemove', dragging);
document.addEventListener('mouseup', stopDrag);