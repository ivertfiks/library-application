function sortBooks() {
    const sortOption = document.getElementById('sort').value;
    const bookList = document.getElementById('book-list');
    const books = Array.from(bookList.getElementsByClassName('book'));

    books.sort((a, b) => {
        const titleA = a.querySelector('.title').textContent.toLowerCase();
        const titleB = b.querySelector('.title').textContent.toLowerCase();
        const authorA = a.querySelector('.author').textContent.toLowerCase();
        const authorB = b.querySelector('.author').textContent.toLowerCase();

        if (sortOption === 'title') {
            return titleA.localeCompare(titleB);
        } else if (sortOption === 'author') {
            return authorA.localeCompare(authorB);
        } else {
            return 0;
        }
    });

    books.forEach(book => bookList.appendChild(book));
}

let currentPage = 1;
const totalPages = 2; // Общее количество страниц, обновите при добавлении новых страниц

document.getElementById('nextPage').addEventListener('click', () => {
    changePage(currentPage + 1);
});

document.getElementById('prevPage').addEventListener('click', () => {
    changePage(currentPage - 1);
});

document.addEventListener('keydown', (event) => {
    if (event.key === 'ArrowRight' || event.key === 'd') {
        changePage(currentPage + 1);
    } else if (event.key === 'ArrowLeft' || event.key === 'a') {
        changePage(currentPage - 1);
    }
});

function changePage(page) {
    if (page < 1 || page > totalPages) {
        return;
    }

    document.getElementById(`page${currentPage}`).style.display = 'none';
    currentPage = page;
    document.getElementById(`page${currentPage}`).style.display = 'block';
    document.getElementById('pageNumber').textContent = `Страница ${currentPage} / ${totalPages}`;
}
