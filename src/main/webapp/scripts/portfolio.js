var closePortfolio = document.getElementById('closePortfolio');
var portfolio = document.getElementById('portfolio');

closePortfolio.onclick = function (ev) {
    portfolio.style.display = "none";
};

$('.portfolioButton').click(function () {
    var managerid = $(this).attr('portfolioid')
    $.get('/Base/portfolio', {
            userId: managerid
        }, function (data) {
            $('#us_name').text(data.username);
            $('#us_surname').text(data.surname);
            $('#us_email').text(data.email);
            $('#us_phone').text(data.phone);
            $('#us_role').text(data.role);
            $('#us_days').text(data.days);
            portfolio.style.display = 'block';
        }
    );
});