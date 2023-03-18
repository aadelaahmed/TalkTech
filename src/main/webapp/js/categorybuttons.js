document.addEventListener("DOMContentLoaded", function(event) {
        const filterButtons = document.querySelectorAll('.filter-button');
        console.log("categoriiiiziiing buttons");
        filterButtons.forEach(button => {
            button.addEventListener('click', () => {
                button.classList.toggle('active');

                const activeFilters = [];

                filterButtons.forEach(button => {
                    if (button.classList.contains('active')) {
                        activeFilters.push(button.getAttribute('data-filter'));
                    }
                });

                console.log("active filters -> "+activeFilters); // This is where you would filter your products based on the selected filters
                const xhr = new XMLHttpRequest();
                const url = "category";
                const params = "filters=" + activeFilters.join(',');
                xhr.open("POST", url);
                xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhr.onreadystatechange = function() {
                  if (xhr.readyState == 4 && xhr.status == 200) {
                    console.log(xhr.responseText); // This is the response from the servlet
                  }
                }
                xhr.send(params);
            });
        });
    });