<?php

session_start();

if (isset($_SESSION['tdata'])) {
    unset($_SESSION['tdata']);
}