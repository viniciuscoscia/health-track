$(document).ready(function() {
    /*------------ Senha Forte ---------------*/
    $.validator.addMethod('strongPassword', function (value, element) {
        return this.optional(element)
            || value.length >= 6
            && /\d/.test(value)
            && /[a-z]/i.test(value);
    }, 'Sua senha precisa ter no mínimo 6 caracteres, combinando letras e números.')

    /*------------ Formato de data ---------------*/
    $.validator.addMethod("dateITA", function (value, element) {
        var d = new Date();
        var check = false,
            re = /^\d{1,2}\/\d{1,2}\/\d{4}$/,
            adata, gg, mm, aaaa, xdata;
        if (re.test(value)) {
            adata = value.split("/");
            gg = parseInt(adata[0], 10);
            mm = parseInt(adata[1], 10);
            aaaa = parseInt(adata[2], 10);
            xdata = new Date(Date.UTC(aaaa, mm - 1, gg, 12, 0, 0, 0));
            if (( xdata.getUTCFullYear() === aaaa ) && ( xdata.getUTCMonth() === mm - 1 ) && ( xdata.getUTCDate() === gg ) && (aaaa <= d.getUTCFullYear())) {
                check = true;
            } else {
                check = false;
            }
        } else {
            check = false;
        }
        return this.optional(element) || check;
    }, $.validator.messages.date);

    /*------------- Formato Hora ---------------*/
    $.validator.addMethod("time24", function (value, element) {
        if (!/^\d{2}:\d{2}$/.test(value)) return false;
        var parts = value.split(':');
        if (parts[0] > 23 || parts[1] > 59) return false;
        return true;
    }, "Horário inválido, use o formato de 24 horas.");
    /*------------- greaterThan ---------------*/
    $.validator.addMethod("greaterThan",
        function (value, element, param) {
            var $min = $(param);
            if (this.settings.onfocusout) {
                $min.off(".validate-greaterThan").on("blur.validate-greaterThan", function () {
                    $(element).valid();
                });
            }
            return parseInt(value) > parseInt($min.val());
        }, "Campo deve apresentar valor maior do que pressão diastólica");


    $(".data").datepicker({
        dateFormat: 'dd/mm/yy',
        dayNames: ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado'],
        dayNamesMin: ['D', 'S', 'T', 'Q', 'Q', 'S', 'S', 'D'],
        dayNamesShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb', 'Dom'],
        monthNames: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
        monthNamesShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
        nextText: '',
        prevText: '',
        changeMonth: true,
        changeYear: true
    });

    $(function () {
        $("#campos-cadastro").validate({
            rules: {
                nome: {
                    required: true
                },
                sobrenome: {
                    required: true
                },
                dataNasc: {
                    required: true,
                    dateITA: true
                },
                altura: {
                    required: true
                },
                email: {
                    required: true,
                    email: true
                },
                confemail: {
                    required: true,
                    equalTo: "#email"
                },
                senha: {
                    required: true,
                    strongPassword: true
                },
                confsenha: {
                    required: true,
                    equalTo: "#senha"
                }
            },
            messages: {
                nome: {
                    required: 'O preenchimento deste campo é obrigatório'
                },
                sobrenome: {
                    required: 'O preenchimento deste campo é obrigatório'
                },
                dataNasc: {
                    required: 'O preenchimento deste campo é obrigatório'
                },
                altura: {
                    required: 'O preenchimento deste campo é obrigatório'
                },
                email: {
                    required: 'O preenchimento deste campo é obrigatório',
                    email: 'Insira um email válido'
                },
                confemail: {
                    required: 'O preenchimento deste campo é obrigatório',
                    equalTo: 'O email e sua confirmação devem ser idênticos',
                    email: 'Insira um email válido'
                },
                senha: {
                    required: 'O preenchimento deste campo é obrigatório'
                },
                confsenha: {
                    required: 'O preenchimento deste campo é obrigatório',
                    equalTo: 'A senha e sua confirmação devem ser idênticas'
                }
            }
        });
    });
    /*------------------------- Peso ------------------*/
    $(function () {
        $("#form-add-peso").validate({
            rules: {
                data: {
                    dateITA: true
                }
            }
        })
    });

    $(function () {
        $("#form-edit-peso").validate({
            rules: {
                data: {
                    required: true,
                    dateITA: true
                }
            }
        })
    });

    /*------------------- Pressão -------------------*/
    $(function () {
        $("#form-add-pressao").validate({
            rules: {
                data: {
                    dateITA: true
                },
                pressistolica: {
                	required: true,
                    greaterThan: "#pressdiastolica"
                },
                pressdiastolica:{
                	required: true
                }
            }
        })
    });

    $(function () {
        $("#form-edit-pressao").validate({
            rules: {
                data: {
                    dateITA: true
                },
                pressistolica: {
                	required: true,
                    greaterThan: "#pressdiastolica"
                },
                pressdiastolica:{
                	required: true
                }
            }
        })
    });

    $("button[id='salvarpressao']").click(function () {
        var dia = parseInt(document.getElementById("pressdiastolica").value);
        var sis = parseInt(document.getElementById("pressistolica").value);
        var cond = $("#condicao");
        if ((sis < 100) && (dia < 60)) {
            cond.val('Pressão Baixa');
        } else if (((sis >= 100) && (sis < 140)) && ((dia >= 60) && (dia < 90))) {
            cond.val('Pressão normal');
        } else if ((sis >= 140) && (dia >= 90)) {
            cond.val('Pressão elevada');
        } else if ((sis >= 140) && (dia < 90)) {
            cond.val('Pressão anormal');
        } else {
            cond.val('Verificar dados');
        }
        console.log(sis);
        console.log(dia);
    });

    /*------------------ Atividade Física --------------*/
    $(function () {
        $("#form-add-ativfisica").validate({
            rules: {
                data: {
                    dateITA: true
                },
                hora: {
                    time24: true
                }
            }
        })
    });

    $(function () {
        $("#form-edit-ativfisica").validate({
            rules: {
                data: {
                    dateITA: true
                },
                hora: {
                    time24: true
                }
            }
        })
    });

    /*---------------- Alimentação -----------------*/
    $(function () {
        $("#form-add-alimentacao").validate({
            rules: {
                data: {
                    dateITA: true
                },
                hora: {
                    time24: true
                }
            }
        });
    });

    $(function () {
        $("#form-edit-alimentacao").validate({
            rules: {
                data: {
                    dateITA: true
                },
                hora: {
                    time24: true
                }
            }
        });
    });

    /*-------------------------- Modificar Perfil ---------------------------*/
    $(function () {
        $("#modificarperfil").validate({
            rules: {
                dataNasc: {
                    dateITA: true
                }
            }
        });
    });

    $(function () {
        $("#trocarsenha").validate({
            rules: {
                senha: {
                    required: true
                },
                novasenha:{
                    required: true,
                    strongPassword: true
                },
                confnovasenha: {
                    required: true,
                    equalTo: "#novasenha"
                }
            }
        });
    });

    //MAIN
    var pacientes = document.querySelectorAll(".paciente");
    var cont;

    for(cont = 0; cont < 5; cont++){
       var altura = pacientes[cont].querySelector(".info-altura").textContent;
       var peso = pacientes[cont].querySelector(".info-peso").textContent;
       var imc = pacientes[cont].querySelector(".info-imc");
       console.log(altura);
       console.log(peso);
       console.log(imc);

       if ((altura <= 0)||(altura >= 2.5)) {
          pacientes[cont].querySelector(".info-altura").textContent = ("Altura inválida");
          //pacientes[cont].style.backgroundColor = "lightcoral";
          pacientes[cont].classList.add("paciente-invalido");
          imc.textContent = ("Verificar dados");
       }else if ((peso <= 0) || (peso >= 500)) {
          pacientes[cont].querySelector(".info-peso").textContent = ("Peso inválido");
          //pacientes[cont].style.backgroundColor = "lightcoral";
          pacientes[cont].classList.add("paciente-invalido");
          imc.textContent = ("Verificar dados");
       }else {
          imc.textContent = (peso / (altura * altura)).toFixed(2);
       }


    }

});

/*$.validator.addMethod("time24", function (value, element) {
	if (!/^\d{2}:\d{2}:\d{2}$/.test(value)) return false;
	var parts = value.split(':');
	if (parts[0] > 23 || parts[1] > 59 || parts[2] > 59) return false;
	return true;
}, "Horário inválido, use o formato de 24 horas.");*/
