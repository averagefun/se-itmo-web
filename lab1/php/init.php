<?php

session_start();

if (isset($_SESSION["tdata"])) foreach ($_SESSION["tdata"] as $rdata) {
    echo <<<HTML
    <tr>
        <td>$rdata[0]</td>
        <td>$rdata[1]</td>
        <td>$rdata[2]</td>
        <td>$rdata[3]</td>
        <td>$rdata[4]</td>
        <td>$rdata[5]</td>
    </tr>
HTML;
} ?>
