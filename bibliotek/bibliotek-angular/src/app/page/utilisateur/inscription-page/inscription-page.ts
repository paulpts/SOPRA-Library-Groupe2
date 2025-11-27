import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { SubscribeRequestDto } from '../../../dto/subscribe-request-dto';
import { UtilisateurService } from '../../../service/utilisateur-service';
import { passwordMatchValidator } from '../../../validator/password-match-validator';

@Component({
  imports: [ CommonModule, ReactiveFormsModule, RouterLink ],
  templateUrl: './inscription-page.html',
  styleUrl: './inscription-page.css',
})
export class InscriptionPage implements OnInit {
  protected subscriptionError: boolean = false;

  protected userForm!: FormGroup;
  protected usernameCtrl!: FormControl;
  protected passwordCtrl!: FormControl;
  protected passwordConfirmCtrl!: FormControl;

  constructor(private utilisateurService: UtilisateurService, private formBuilder: FormBuilder, private router: Router) { }

  ngOnInit(): void {
    this.usernameCtrl = this.formBuilder.control('', Validators.required);
    this.passwordCtrl = this.formBuilder.control('', [ Validators.required, Validators.minLength(6) ]);
    this.passwordConfirmCtrl = this.formBuilder.control('', [ Validators.required, Validators.minLength(6) ]);

    this.userForm = this.formBuilder.group({
      username: this.usernameCtrl,
      password: this.passwordCtrl,
      passwordConfirm: this.passwordConfirmCtrl
    }, {
      validators: passwordMatchValidator('password', 'passwordConfirm')
    });
  }

  public async connecter() {
    try {
      await this.utilisateurService.subscribe(new SubscribeRequestDto(this.usernameCtrl.value, this.passwordCtrl.value));

      this.router.navigate([ '/login' ]);
    }

    // Si la connexion n'a pas pu se faire, affichage de l'erreur sur le template
    catch {
      this.subscriptionError = true;
    }
  }
}
