<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="categories" required="true" type="java.util.Collection" %>
<%@ attribute name="searchForm" required="true" type="ru.belyaev.shop.form.SearchForm" %>

<div class="panel-heading">Category filters</div>
<div class="panel-body categories">
    <label><input type="checkbox" id="allCategories"> All</label>
    <c:forEach var="category" items="${categories}">
        <div class="form-group">
            <div class="checkbox">
                <label><input type="checkbox" name="category" value="${category.id}" class="search-option" ${searchForm.categories.contains(category.id) ? 'checked' : ''}>${category.name}</label>
            </div>
        </div>
    </c:forEach>
</div>