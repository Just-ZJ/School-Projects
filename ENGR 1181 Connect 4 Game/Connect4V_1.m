clc
clear
% Version 1.1
% Date: 1 November 2019
% Connect 4 game is created
% Players are can give themselves nicknames 
% Players will be assigned with the color black or red 
% Players are able to put their pieces anywhere on the board

%gets nicknames of players through user input
player1 = input('Player 1, please enter your nickname: ', 's');
player2 = input('Player 2, please enter your nickname: ', 's');
%assigns color to each player
fprintf('%s, you will be black.  \n', player1)
fprintf('%s, you will be red \n', player2)

% Initialize scene
my_scene = simpleGameEngine('ConnectFour.png',86,101);

% Set up variables to name the various sprites
empty_sprite = 1;
red_sprite = 2;
black_sprite = 3;

% Display empty board   
board_display = empty_sprite * ones(6,7);
drawScene(my_scene,board_display)





for(i = 1: 1: 100)
    %get mouse input
    [row,col] = getMouseInput(my_scene);

    %player _____ that starts first
    if(mod(i, 2) ~= 1)
        %display updated board with player1 choice
        board_display(row,col) = black_sprite;
    elseif(mod(i, 2) == 1)
        %display updated board with player2 choice
        board_display(row,col) = red_sprite;
    end
    
    %displays the updated board
    drawScene(my_scene,board_display)
end





