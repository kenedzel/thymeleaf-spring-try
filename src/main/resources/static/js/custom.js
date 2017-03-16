/**
 * Created by kenneth on 3/16/17.
 */
$.extend( true, $.fn.dataTable.defaults, {
    "ordering": false
} );
$(document).ready(function () {
    $('#data-table').DataTable();
});
