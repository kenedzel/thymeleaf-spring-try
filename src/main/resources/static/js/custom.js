/**
 * Created by kenneth on 3/16/17.
 */
$.extend( true, $.fn.dataTable.defaults, {
    "ordering": false
} );
$(document).ready(function () {
    $('#data-table').DataTable();
    $('.selectpicker').selectpicker({
        size: 8,
        tickIcon: ""
    });
});
function passOnClick(id)
{
    var deleteButton = document.getElementById("deleteButton");
    deleteButton.setAttribute("href", "/products/delete/" + id + "");
//delete id parameter

}

