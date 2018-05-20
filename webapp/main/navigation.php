<div class="row">
                                      <!-- <div class="col text-left">
                                          <button type="button" class="btn btn-outline-warning btn-sm"><<</button>
                                      </div>
                                      <div class="col text-center">
                                          <button type="button" class="btn btn-outline-danger btn-sm">Pin change</button>
                                      </div>
                                      <div class="col text-right">
                                          <button type="button" class="btn btn-outline-warning btn-sm">>></button>
                                      </div>

                                      -->
<div class="col-12  text-center">
    <div class="btn-group btn-group-sm" role="group" aria-label="Basic example" id="card_buttons">
        <button onclick="location.href='main.php?cardid=1';" type="button" class="btn btn-outline-warning btn-secondary">First card</button>
        <button onclick="location.href='main.php?cardid=2';" type="button" class="btn btn-outline-warning btn-secondary">Second card</button>
        <button onclick="location.href='main.php?cardid=3';" type="button" class="btn btn-outline-warning btn-secondary">Third card</button>
    </div>
</div>
</div>
<script>
// Add active class to the current button (highlight it)
var header = document.getElementById("card_buttons");
var btns = header.getElementsByClassName("btn");
for (var i = 0; i < btns.length; i++) {
  btns[i].addEventListener("click", function() {
    var current = document.getElementsByClassName("active");
    current[0].className = current[0].className.replace(" active", "");
    this.className += " active";
  });
}
</script>
