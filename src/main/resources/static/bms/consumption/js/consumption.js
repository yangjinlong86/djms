$(document).ready(function () {
    $("#addOrEditConsumption").hide();
    $("#consumptionTable").show();

    $("#addConsumptionBtn").click(function () {
        $("#addOrEditConsumption").show();
        $("#consumptionTable").hide();
    });

    $("#closeFormBtn").click(function () {
        $("#addOrEditConsumption").hide();
        $("#consumptionTable").show();
    });
});