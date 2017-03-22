/**
 * Created by kenneth on 3/16/17.
 */
$.extend( true, $.fn.dataTable.defaults, {
    "ordering": false
} );
$(document).ready(function () {
    $('#data-table').DataTable();
});
function passOnClick(id)
{
    var deleteButton = document.getElementById("deleteButton");
    deleteButton.setAttribute("href", "/products/delete/" + id + "");
//delete id parameter

}

